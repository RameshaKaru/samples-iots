/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.wso2iots.internal;

import static org.openhab.binding.wso2iots.Wso2iotsBindingConstants.*;

//import java.util.Collections;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.wso2iots.handler.BridgeHandler;
import org.openhab.binding.wso2iots.handler.BuildingMonitorHandler;
import org.osgi.service.component.annotations.Component;

import com.google.common.collect.ImmutableSet;

/**
 * The {@link Wso2iotsHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Ramesha Karunasena - Initial contribution
 */
@Component(service = ThingHandlerFactory.class, immediate = true)
@NonNullByDefault
public class Wso2iotsHandlerFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = ImmutableSet.of(THING_TYPE_BRIDGE,
            THING_TYPE_BUILDINGMONITOR);

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(THING_TYPE_BRIDGE)) {
            BridgeHandler bridge = new BridgeHandler((Bridge) thing);
            return bridge;
        } else if (thingTypeUID.equals(THING_TYPE_BUILDINGMONITOR)) {
            return new BuildingMonitorHandler(thing);
        }

        return null;
    }
}
