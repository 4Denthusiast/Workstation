/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.workstation.ui;

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.logic.characters.CharacterComponent;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;

public abstract class BaseWorkstationScreen extends CoreScreenLayer {
    @In
    private LocalPlayer localPlayer;

    private EntityRef previousWorkstation;

    protected EntityRef getWorkstation() {
        EntityRef characterEntity = localPlayer.getCharacterEntity();
        CharacterComponent characterComponent = characterEntity.getComponent(CharacterComponent.class);
        if (previousWorkstation != characterComponent.predictedInteractionTarget && characterComponent.predictedInteractionTarget != null) {
            previousWorkstation = characterComponent.predictedInteractionTarget;
            initializeWorkstation(previousWorkstation);
        }

        return previousWorkstation;
    }

    @Override
    public void onOpened() {
        super.onOpened();
        getWorkstation();
    }

    protected abstract void initializeWorkstation(EntityRef workstation);
}
