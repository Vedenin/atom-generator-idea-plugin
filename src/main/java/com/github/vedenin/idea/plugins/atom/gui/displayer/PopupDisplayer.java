package com.github.vedenin.idea.plugins.atom.gui.displayer;

import com.github.vedenin.idea.plugins.atom.factory.PopupChooserBuilderFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.PopupChooserBuilder;

import javax.swing.JList;

public class PopupDisplayer {

    static final String TITLE = "Builder not found 1 ";

    private PopupChooserBuilderFactory popupChooserBuilderFactory;

    public PopupDisplayer(PopupChooserBuilderFactory popupChooserBuilderFactory) {
        this.popupChooserBuilderFactory = popupChooserBuilderFactory;
    }

    public void displayPopupChooser(Editor editor, JList list, Runnable runnable) {
        PopupChooserBuilder builder = popupChooserBuilderFactory.getPopupChooserBuilder(list);
        builder.setTitle(TITLE).
                setItemChoosenCallback(runnable).
                setMovable(true).
                createPopup().showInBestPositionFor(editor);
    }
}
