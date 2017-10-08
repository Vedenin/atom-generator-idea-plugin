package com.github.vedenin.idea.plugins.atom.gui.displayer;

import com.github.vedenin.idea.plugins.atom.factory.PopupChooserBuilderFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.PopupChooserBuilder;
import lombok.RequiredArgsConstructor;

import javax.swing.JList;

import static com.github.vedenin.idea.plugins.atom.Constants.POPUP_TITLE;

@RequiredArgsConstructor
public class PopupDisplayer {

    private final PopupChooserBuilderFactory popupChooserBuilderFactory;

    public void displayPopupChooser(Editor editor, JList list, Runnable runnable) {
        PopupChooserBuilder builder = popupChooserBuilderFactory.getPopupChooserBuilder(list);
        builder.setTitle(POPUP_TITLE).
                setItemChoosenCallback(runnable).
                setMovable(true).
                createPopup().showInBestPositionFor(editor);
    }
}
