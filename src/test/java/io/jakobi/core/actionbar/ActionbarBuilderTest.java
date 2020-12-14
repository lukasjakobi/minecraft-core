package io.jakobi.core.actionbar;

import org.junit.Test;

public class ActionbarBuilderTest {

    private final String actionbarText = "Test";
    private final int actionbarShowTime = 10;

    @Test
    public void testBuildActionbar() {
        ActionbarBuilder actionbarBuilder = new ActionbarBuilder(actionbarText);
        actionbarBuilder.setShowTime(actionbarShowTime);

        assert actionbarBuilder.getShowTime() == actionbarShowTime;
        assert actionbarBuilder.getText().equals(actionbarText);
    }
}
