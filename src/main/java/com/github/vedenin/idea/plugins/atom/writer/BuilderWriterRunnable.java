package com.github.vedenin.idea.plugins.atom.writer;

import com.intellij.openapi.application.Application;
import com.github.vedenin.idea.plugins.atom.psi.BuilderPsiClassBuilder;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuilderWriterRunnable implements Runnable {

    private PsiHelper psiHelper = new PsiHelper();
    private final BuilderPsiClassBuilder builderPsiClassBuilder;
    private final BuilderContext context;

    @Override
    public void run() {
        psiHelper.getApplication().runWriteAction(
                new BuilderWriterComputable(builderPsiClassBuilder, context)
        );
    }
}