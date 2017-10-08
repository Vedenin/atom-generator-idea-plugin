package com.github.vedenin.idea.plugins.atom.gui;

import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.ide.util.PackageChooserDialog;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiPackage;
import com.intellij.ui.ReferenceEditorComboWithBrowseButton;
import com.github.vedenin.idea.plugins.atom.factory.PackageChooserDialogFactory;
import lombok.RequiredArgsConstructor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class ChooserDisplayerActionListener implements ActionListener {
    private final ReferenceEditorComboWithBrowseButton comboWithBrowseButton;
    private final PackageChooserDialogFactory packageChooserDialogFactory;
    private final Project project;


    @Override
    public void actionPerformed(ActionEvent e) {
        PackageChooserDialog chooser =
                packageChooserDialogFactory.getPackageChooserDialog(CodeInsightBundle.message("dialog.create.class.package.chooser.title"), project);
        chooser.selectPackage(comboWithBrowseButton.getText());
        chooser.show();
        PsiPackage aPackage = chooser.getSelectedPackage();
        if (aPackage != null) {
            comboWithBrowseButton.setText(aPackage.getQualifiedName());
        }
    }
}
