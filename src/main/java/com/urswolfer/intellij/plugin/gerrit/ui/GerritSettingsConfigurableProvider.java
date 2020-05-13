/*
 * Copyright 2020 Urs Wolfer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.urswolfer.intellij.plugin.gerrit.ui;

import com.google.inject.Inject;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.VcsConfigurableProvider;
import com.urswolfer.intellij.plugin.gerrit.GerritModule;
import com.urswolfer.intellij.plugin.gerrit.settings.GerritSettings;
import org.jetbrains.annotations.Nullable;

/**
 * @author Réda Housni Alaoui
 */
public class GerritSettingsConfigurableProvider implements VcsConfigurableProvider {

    @Inject
    private GerritSettings gerritSettings;
    @Inject
    private GerritUpdatesNotificationComponent gerritUpdatesNotificationComponent;

    @Nullable
    @Override
    public Configurable getConfigurable(Project project) {
        return new GerritSettingsConfigurable(project, gerritSettings, gerritUpdatesNotificationComponent);
    }

    public static class Proxy extends GerritSettingsConfigurableProvider {
        private GerritSettingsConfigurableProvider delegate;

        public Proxy() {
            delegate = GerritModule.getInstance(GerritSettingsConfigurableProvider.class);
        }

        @Nullable
        @Override
        public Configurable getConfigurable(Project project) {
            return delegate.getConfigurable(project);
        }
    }
}
