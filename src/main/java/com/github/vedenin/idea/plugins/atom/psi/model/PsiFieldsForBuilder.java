package com.github.vedenin.idea.plugins.atom.psi.model;

import com.google.common.collect.ImmutableList;
import com.intellij.psi.PsiField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PsiFieldsForBuilder {
    private final List<PsiField> fieldsForSetters;
    private final List<PsiField> fieldsForConstructor;
    private final List<PsiField> allSelectedFields;
}
