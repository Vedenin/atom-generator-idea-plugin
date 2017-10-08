package com.github.vedenin.atoms.openapi;

import com.intellij.openapi.project.Project;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "getAtom")
public class ProjectAtom {
    private final Project project;

}
