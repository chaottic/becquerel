package com.chaottic.becquerel.common.component;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;

public interface GrayComponent extends ComponentV3, AutoSyncedComponent {

    double getGray();

    void setGray(double gray);

    void addGray(double gray);
}
