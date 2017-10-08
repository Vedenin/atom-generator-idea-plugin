package com.github.vedenin.idea.plugins.atom.writer;

import com.github.vedenin.idea.plugins.atom.psi.BuilderPsiClassBuilder;
import com.intellij.openapi.command.CommandProcessor;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuilderWriter {
    static final String CREATE_BUILDER_STRING = "Create Builder";
    private final BuilderPsiClassBuilder builderPsiClassBuilder;
    private final PsiHelper psiHelper;

    public void writeBuilder(BuilderContext context) {
        CommandProcessor commandProcessor = psiHelper.getCommandProcessor();
        commandProcessor.executeCommand(context.getProject(),
                new BuilderWriterRunnable(builderPsiClassBuilder, context), CREATE_BUILDER_STRING, this);
    }
}
