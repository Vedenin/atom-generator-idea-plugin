package com.github.vedenin.atoms.openapi;

import com.github.vedenin.atom.annotations.Atom;
import com.intellij.CommonBundle;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import lombok.NoArgsConstructor;

import javax.swing.Icon;

@Atom(Messages.class)
@NoArgsConstructor(staticName = "getAtom")
public class MessagesAtom {
    public void showErrorDialog(Project project, String message, String title) {
        Messages.showErrorDialog(project, message, title);
    }

    public void showMessageDialog(Project project, String message, String title, Icon icon) {
        Messages.showMessageDialog(project, message, title, icon);
    }

    public void showErrorInMessageDialog(Project project, String message) {
        Messages.showMessageDialog(project, message, CommonBundle.getErrorTitle(), Messages.getErrorIcon());
    }
}
