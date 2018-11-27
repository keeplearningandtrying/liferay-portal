import Component from 'metal-component';
import {Config} from 'metal-state';
import {Drag, DragDrop} from 'metal-drag-drop';
import position from 'metal-position';
import Soy from 'metal-soy';

import './FragmentEntryLink.es';
import {
	CLEAR_DRAG_TARGET,
	MOVE_FRAGMENT_ENTRY_LINK,
	UPDATE_DRAG_TARGET,
	UPDATE_LAST_SAVE_DATE,
	UPDATE_SAVING_CHANGES_STATUS
} from '../../actions/actions.es';
import {
	DROP_TARGET_BORDERS,
	DROP_TARGET_ITEM_TYPES
} from '../../reducers/placeholders.es';
import {getFragmentRowIndex, setIn} from '../../utils/utils.es';
import state from '../../store/state.es';
import templates from './FragmentEntryLinkList.soy';

/**
 * FragmentEntryLinkList
 * @review
 */
class FragmentEntryLinkList extends Component {

	/**
	 * Adds drop target types to state
	 * @param {Object} _state
	 * @private
	 * @return {Object}
	 * @static
	 */
	static _addDropTargetItemTypesToState(_state) {
		return setIn(_state, ['dropTargetItemTypes'], DROP_TARGET_ITEM_TYPES);
	}

	/**
	 * Returns whether a drop is valid or not
	 * @param {Object} eventData
	 * @private
	 * @return {boolean}
	 * @static
	 */
	static _dropValid(eventData) {
		const sourceData = eventData.source.dataset;
		const targetData = eventData.target ? eventData.target.dataset : null;

		const {
			dropTargetItemType
		} = FragmentEntryLinkList._getDropTargetItemData(eventData);

		const targetIsSameFragment = (
			(dropTargetItemType === DROP_TARGET_ITEM_TYPES.fragment) &&
			(sourceData.fragmentEntryLinkId === targetData.fragmentEntryLinkId)
		);

		return (dropTargetItemType && !targetIsSameFragment);
	}

	/**
	 * Get hovered element data
	 * @param {!Object} eventData
	 * @private
	 * @return {Object}
	 * @static
	 */
	static _getDropTargetItemData(eventData) {
		let dropTargetItemId = null;
		let dropTargetItemType = null;

		const targetData = eventData.target ? eventData.target.dataset : null;

		if (targetData) {
			if ('columnId' in targetData) {
				dropTargetItemId = targetData.columnId;
				dropTargetItemType = DROP_TARGET_ITEM_TYPES.column;
			}
			else if ('fragmentEntryLinkId' in targetData) {
				dropTargetItemId = targetData.fragmentEntryLinkId;
				dropTargetItemType = DROP_TARGET_ITEM_TYPES.fragment;
			}
			else if ('layoutSectionId' in targetData) {
				dropTargetItemId = targetData.layoutSectionId;
				dropTargetItemType = DROP_TARGET_ITEM_TYPES.section;
			}
			else if ('fragmentEmptyList' in targetData) {
				dropTargetItemType = DROP_TARGET_ITEM_TYPES.fragmentList;
			}
		}

		return {
			dropTargetItemId,
			dropTargetItemType
		};
	}

	/**
	 * Checks wether a section is empty or not, sets empty parameter
	 * and returns a new state
	 * @param {Object} _state
	 * @private
	 * @return {Object}
	 * @static
	 */
	static _setEmptySections(_state) {
		return setIn(
			_state,
			[
				'layoutData',
				'structure'
			],
			_state.layoutData.structure.map(
				section => setIn(
					section,
					['empty'],
					section.columns.every(
						column => column.fragmentEntryLinkIds.length === 0
					)
				)
			)
		);
	}

	/**
	 * @inheritDoc
	 * @private
	 * @review
	 */
	attached() {
		this._initializeDragAndDrop();
	}

	/**
	 * @inheritDoc
	 * @private
	 * @review
	 */
	dispose() {
		this._dragDrop.dispose();
	}

	/**
	 * @inheritDoc
	 * @private
	 * @review
	 */
	prepareStateForRender(state) {
		let _state = FragmentEntryLinkList._addDropTargetItemTypesToState(state);

		_state = FragmentEntryLinkList._setEmptySections(_state);

		return _state;
	}

	/**
	 * Gives focus to the specified fragmentEntryLinkId
	 * @param {string} fragmentEntryLinkId
	 * @review
	 */
	focusFragmentEntryLink(fragmentEntryLinkId) {
		requestAnimationFrame(
			() => {
				const fragmentEntryLinkElement = this.refs[fragmentEntryLinkId];

				if (fragmentEntryLinkElement) {
					fragmentEntryLinkElement.focus();
					fragmentEntryLinkElement.scrollIntoView();
				}
			}
		);
	}

	/**
	 * Callback that is executed when an item is being dragged.
	 * @param {object} eventData
	 * @param {MouseEvent} eventData.originalEvent
	 * @private
	 * @review
	 */
	_handleDrag(eventData) {
		if (FragmentEntryLinkList._dropValid(eventData)) {
			const mouseY = eventData.originalEvent.clientY;
			const targetItemRegion = position.getRegion(eventData.target);

			const {
				dropTargetItemId,
				dropTargetItemType
			} = FragmentEntryLinkList._getDropTargetItemData(eventData);

			this._targetBorder = DROP_TARGET_BORDERS.bottom;

			if (Math.abs(mouseY - targetItemRegion.top) <=
				Math.abs(mouseY - targetItemRegion.bottom)) {
				this._targetBorder = DROP_TARGET_BORDERS.top;
			}

			this.store.dispatchAction(
				UPDATE_DRAG_TARGET,
				{
					dropTargetBorder: this._targetBorder,
					dropTargetItemId,
					dropTargetItemType
				}
			);
		}
	}

	/**
	 * Callback that is executed when we leave a drag target.
	 * @private
	 * @review
	 */
	_handleDragEnd() {
		this.store.dispatchAction(
			CLEAR_DRAG_TARGET
		);
	}

	/**
	 * Callback that is executed when an item is dropped.
	 * @param {object} data
	 * @param {MouseEvent} event
	 * @private
	 * @review
	 */
	_handleDrop(data, event) {
		event.preventDefault();

		if (FragmentEntryLinkList._dropValid(data)) {
			requestAnimationFrame(
				() => {
					this._initializeDragAndDrop();
				}
			);

			this.store
				.dispatchAction(
					UPDATE_SAVING_CHANGES_STATUS,
					{
						savingChanges: true
					}
				)
				.dispatchAction(
					MOVE_FRAGMENT_ENTRY_LINK,
					{
						fragmentEntryLinkId:
							data.source.dataset.fragmentEntryLinkId
					}
				)
				.dispatchAction(
					UPDATE_LAST_SAVE_DATE,
					{
						lastSaveDate: new Date()
					}
				)
				.dispatchAction(
					UPDATE_SAVING_CHANGES_STATUS,
					{
						savingChanges: false
					}
				)
				.dispatchAction(
					CLEAR_DRAG_TARGET
				);
		}
	}

	/**
	 * @param {object} event
	 * @private
	 * @review
	 */
	_handleFragmentMove(event) {
		const placeholderId = event.fragmentEntryLinkId;

		const rowIndex = getFragmentRowIndex(
			this.layoutData.structure,
			placeholderId
		);

		let targetId;

		const targetRow = this
			.layoutData
			.structure[rowIndex + event.direction];

		if (targetRow && targetRow.columns.length) {
			targetId = targetRow.columns[0].fragmentEntryLinkIds[0];
		}

		if (event.direction === 1) {
			this._targetBorder = DROP_TARGET_BORDERS.bottom;
		}
		else {
			this._targetBorder = DROP_TARGET_BORDERS.top;
		}

		if (targetId && targetId !== placeholderId) {
			this.store
				.dispatchAction(
					UPDATE_SAVING_CHANGES_STATUS,
					{
						savingChanges: true
					}
				)
				.dispatchAction(
					MOVE_FRAGMENT_ENTRY_LINK,
					{
						originFragmentEntryLinkId: placeholderId,
						targetFragmentEntryLinkBorder: this._targetBorder,
						targetFragmentEntryLinkId: targetId
					}
				)
				.dispatchAction(
					UPDATE_LAST_SAVE_DATE,
					{
						lastSaveDate: new Date()
					}
				)
				.dispatchAction(
					UPDATE_SAVING_CHANGES_STATUS,
					{
						savingChanges: false
					}
				);
		}
	}

	/**
	 * @private
	 * @review
	 */
	_initializeDragAndDrop() {
		if (this._dragDrop) {
			this._dragDrop.dispose();
		}

		this._dragDrop = new DragDrop(
			{
				autoScroll: true,
				dragPlaceholder: Drag.Placeholder.CLONE,
				handles: '.drag-handler',
				sources: '.drag-fragment',
				targets: '.fragment-entry-link-drop-target'
			}
		);

		this._dragDrop.on(
			DragDrop.Events.DRAG,
			this._handleDrag.bind(this)
		);

		this._dragDrop.on(
			DragDrop.Events.END,
			this._handleDrop.bind(this)
		);

		this._dragDrop.on(
			DragDrop.Events.TARGET_LEAVE,
			this._handleDragEnd.bind(this)
		);
	}

}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
FragmentEntryLinkList.STATE = {

	/**
	 * Data associated to the layout
	 * @default {object}
	 * @instance
	 * @memberOf FragmentEntryLinkList
	 * @review
	 * @type {object}
	 */
	layoutData: state.layoutData,

	/**
	 * Internal DragDrop instance.
	 * @default null
	 * @instance
	 * @memberOf FragmentEntryLinkList
	 * @review
	 * @type {object|null}
	 */
	_dragDrop: Config.internal().value(null),

	/**
	 * Nearest border of the hovered fragment while dragging
	 * @default undefined
	 * @instance
	 * @memberOf FragmentEntryLinkList
	 * @review
	 * @type {!string}
	 */
	_targetBorder: Config.internal().string()
};

Soy.register(FragmentEntryLinkList, templates);

export {FragmentEntryLinkList};
export default FragmentEntryLinkList;