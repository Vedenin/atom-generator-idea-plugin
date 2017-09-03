package com.github.vedenin.idea.plugins.atom.writer;

import com.github.vedenin.idea.plugins.atom.psi.BuilderPsiClassBuilder;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BuilderWriterTest {

    @InjectMocks private BuilderWriter builderWriter;
    @Mock private PsiHelper psiHelper;
    @Mock private BuilderPsiClassBuilder builderPsiClassBuilder;
    @Mock private BuilderContext context;
    @Mock private Project project;

    @Test
    public void shouldExecuteCommandWithRunnable() {
        // given
        CommandProcessor commandProcessor = mock(CommandProcessor.class);
        given(psiHelper.getCommandProcessor()).willReturn(commandProcessor);
        given(context.getProject()).willReturn(project);

        // when
        builderWriter.writeBuilder(context);

        // then
        verify(commandProcessor).executeCommand(eq(project), isA(BuilderWriterRunnable.class), eq(BuilderWriter.CREATE_BUILDER_STRING), eq(builderWriter));
    }
}
