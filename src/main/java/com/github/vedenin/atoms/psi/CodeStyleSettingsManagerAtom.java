package com.github.vedenin.atoms.psi;

import com.github.vedenin.atom.annotations.Atom;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;

@Atom(CodeStyleSettingsManager.class)
public class CodeStyleSettingsManagerAtom {
    public String getFieldNamePrefix() {
        return CodeStyleSettingsManager.getInstance().getCurrentSettings().FIELD_NAME_PREFIX;
    }

    public String getParameterNamePrefix() {
        return CodeStyleSettingsManager.getInstance().getCurrentSettings().PARAMETER_NAME_PREFIX;
    }

    public static CodeStyleSettingsManagerAtom createAtom() {
        return new CodeStyleSettingsManagerAtom();
    }
}
