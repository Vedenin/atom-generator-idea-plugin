package com.github.vedenin.idea.plugins.atom;

import com.intellij.openapi.util.IconLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.swing.Icon;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    public static final String TEXT_ADDITIONAL_ACTION = "Create New Builder...";
    public static final Icon ICON_ADDITIONAL_ACTION = IconLoader.getIcon("/actions/intentionBulb.png");
    public static final String BUILDER_SUFFIX = "Builder";
    public static final String BUILDER_DIALOG_METHOD_PREFIX = "with";
    public static final String BUILDER_DIALOG_NAME = "CreateBuilder";
    public static final String FINDER_SEARCH_PATTERN = "Builder";
    public static final String MEMBER_CHOOSE_DIALOG_TITLE = "Select Fields to Be Available in Builder";
    public static final String POPUP_TITLE = "Builder not found 2 ";
    public static final String WRITER_CREATE_BUILDER_STRING = "Create Builder";
}
