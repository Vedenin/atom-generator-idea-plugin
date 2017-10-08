package com.github.vedenin.idea.plugins.atom.action;

import com.intellij.openapi.util.IconLoader;
import org.junit.Before;
import org.junit.Test;

import javax.swing.Icon;

import static org.assertj.core.api.Assertions.assertThat;

public class GoToBuilderAdditionalActionTest {
    private static final String TEXT = "Create New Builder...";
    private static final Icon ICON = IconLoader.getIcon("/actions/intentionBulb.png");


    private GoToBuilderAdditionalAction action;

    @Before
    public void setUp() {
        action = new GoToBuilderAdditionalAction();
    }

    @Test
    public void shouldGetItsOwnText() {
        // when
        String result = action.getText();

        // then
        assertThat(result).isEqualTo(TEXT);
    }

    @Test
    public void shouldGetItsOwnIcon() {
        // when
        Icon result = action.getIcon();

        // then
        assertThat(result).isEqualTo(ICON);
    }

    @Test
    public void shouldDoNothingWhenInvokingExecute() {
        // when
        action.execute();
    }
}
