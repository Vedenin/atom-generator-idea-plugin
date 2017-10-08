package com.github.vedenin.idea.plugins.atom.factory;

import com.intellij.ide.util.PackageChooserDialog;
import com.intellij.openapi.project.Project;

public class PackageChooserDialogFactory {

    public PackageChooserDialog getPackageChooserDialog(String message, Project project) {
        return new PackageChooserDialog(message, project);
    }
}
