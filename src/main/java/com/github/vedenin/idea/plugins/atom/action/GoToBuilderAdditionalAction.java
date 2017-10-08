package com.github.vedenin.idea.plugins.atom.action;

import com.intellij.codeInsight.navigation.GotoTargetHandler;
import com.intellij.openapi.util.IconLoader;

import javax.swing.Icon;

import static com.github.vedenin.idea.plugins.atom.Constants.ICON_ADDITIONAL_ACTION;
import static com.github.vedenin.idea.plugins.atom.Constants.TEXT_ADDITIONAL_ACTION;

public class GoToBuilderAdditionalAction implements GotoTargetHandler.AdditionalAction {
    @Override
    public String getText() {
        return TEXT_ADDITIONAL_ACTION;
    }

    @Override
    public Icon getIcon() {
        return ICON_ADDITIONAL_ACTION;
    }

    @Override
    public void execute() {
    }
}
