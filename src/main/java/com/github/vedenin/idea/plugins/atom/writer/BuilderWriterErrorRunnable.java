package com.github.vedenin.idea.plugins.atom.writer;

import com.github.vedenin.atoms.openapi.MessagesAtom;
import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.openapi.project.Project;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuilderWriterErrorRunnable implements Runnable {

    static final String INTENTION_ERROR_CANNOT_CREATE_CLASS_MESSAGE = "intention.error.cannot.create.class.message";
    static final String INTENTION_ERROR_CANNOT_CREATE_CLASS_TITLE = "intention.error.cannot.create.class.title";

    private final Project project;
    private final String className;
    private final MessagesAtom messagesAtom = MessagesAtom.getAtom();

    @Override
    public void run() {
        messagesAtom.showErrorDialog(project,
                 CodeInsightBundle.message(INTENTION_ERROR_CANNOT_CREATE_CLASS_MESSAGE, className),
                 CodeInsightBundle.message(INTENTION_ERROR_CANNOT_CREATE_CLASS_TITLE));
    }
}
