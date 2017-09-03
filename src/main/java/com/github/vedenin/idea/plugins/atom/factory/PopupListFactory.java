package com.github.vedenin.idea.plugins.atom.factory;

import com.github.vedenin.idea.plugins.atom.action.GoToBuilderAdditionalAction;
import com.github.vedenin.idea.plugins.atom.renderer.ActionCellRenderer;
import com.intellij.ui.components.JBList;

import javax.swing.JList;

import static java.util.Arrays.asList;

public class PopupListFactory {

    private ActionCellRenderer actionCellRenderer;

    @SuppressWarnings("unchecked")
    public JList getPopupList() {
        JList list = new JBList(asList(new GoToBuilderAdditionalAction()));
        list.setCellRenderer(cellRenderer());
        return list;
    }

    private ActionCellRenderer cellRenderer() {
        if (actionCellRenderer == null) {
            actionCellRenderer = new ActionCellRenderer();
        }
        return actionCellRenderer;
    }
}
