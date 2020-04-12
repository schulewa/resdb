function HistoricalDateCellEditor() {

}

HistoricalDateCellEditor.prototype.init = function (params) {
    // create the cell
    this.eInput = document.createElement('input');
    this.eInput.value = params.value;
};

// gets called once when gris is ready to insert the element
HistoricalDateCellEditor.prototype.getGui = function () {
    return this.eInput;
};

// focus and select can be done after the gui is attached
HistoricalDateCellEditor.prototype.afterGuiAttached = function () {
    this.eInput.focus();
    this.eInput.select();
};

// returns the new value after editing
HistoricalDateCellEditor.prototype.getValue = function () {
    return this.eInput.value;
};

// any cleanup we need to be done here
HistoricalDateCellEditor.prototype.destroy = function () {
    // this editor is simple, no cleanup required.
    // this method could be left out as it is optional
};

// if true then this editor will appear in a popup
HistoricalDateCellEditor.prototype.isPopup = function () {
    // and we could leave this method also; false is the default
    return false;
};
