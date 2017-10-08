package com.github.vedenin.idea.plugins.atom.writer;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.github.vedenin.idea.plugins.atom.psi.model.PsiFieldsForBuilder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(exclude = {"isUseSingleField", "isHasButMethod", "isInner"})
public class BuilderContext {
    private final Project project;
    private final PsiFieldsForBuilder psiFieldsForBuilder;
    private final PsiDirectory targetDirectory;
    private final String className;
    private final PsiClass psiClassFromEditor;
    private final String methodPrefix;
    private final boolean isInner;
    private final boolean isHasButMethod;
    private final boolean isUseSingleField;
}
