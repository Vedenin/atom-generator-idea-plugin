package com.github.vedenin.idea.plugins.atom.writer;

import com.github.vedenin.idea.plugins.atom.psi.BuilderPsiClassBuilder;
import com.intellij.openapi.command.CommandProcessor;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import lombok.AllArgsConstructor;

import static com.github.vedenin.idea.plugins.atom.Constants.WRITER_CREATE_BUILDER_STRING;

@AllArgsConstructor
public class BuilderWriter {
    private final BuilderPsiClassBuilder builderPsiClassBuilder;
    private final PsiHelper psiHelper;

    public void writeBuilder(BuilderContext context) {
        psiHelper.getCommandProcessor()
                .executeCommand(
                            context.getProject(),
                            new BuilderWriterRunnable(builderPsiClassBuilder,
                            context
                        ),
                        WRITER_CREATE_BUILDER_STRING,
                        this
                );
    }
}
