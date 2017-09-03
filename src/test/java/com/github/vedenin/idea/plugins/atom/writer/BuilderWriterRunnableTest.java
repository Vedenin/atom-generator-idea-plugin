package com.github.vedenin.idea.plugins.atom.writer;

import com.github.vedenin.idea.plugins.atom.psi.BuilderPsiClassBuilder;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import com.intellij.openapi.application.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@RunWith(MockitoJUnitRunner.class)
public class BuilderWriterRunnableTest {

    private BuilderWriterRunnable builderWriterRunnable;

    @Mock private PsiHelper psiHelper;
    @Mock private BuilderPsiClassBuilder builderPsiClassBuilder;
    @Mock private BuilderContext context;

    @Before
    public void setUp() {
        builderWriterRunnable = new BuilderWriterRunnable(builderPsiClassBuilder, context);
        setField(builderWriterRunnable, "psiHelper", psiHelper);
    }

    @Test
    public void shouldRunWriteActionWithBuilderWriterComputable() {
        // given
        Application application = mock(Application.class);
        given(psiHelper.getApplication()).willReturn(application);

        // when
        builderWriterRunnable.run();

        // then
        verify(application).runWriteAction(any(BuilderWriterComputable.class));
    }
}
