/**
 *
 * Hubitat Import URL: https://raw.githubusercontent.com/PrayerfulDrop/Hubitat/master/Roomba/Roomba-device.groovy
 *
 *  ****************  Roomba Driver  ****************
 *
 *  Design Usage:
 *  This driver supports the Roomba Scheduler App which is designed to integrate any WiFi enabled Roomba devices to have direct local connectivity and integration into Hubitat.  This applicatin will create a Roomba device based on the 
 *  the name you gave your Roomba device in the cloud app.  With this app you can schedule multiple cleaning times, automate cleaning when presence is away, receive notifications about status
 *  of the Roomba (stuck, cleaning, died, etc) and also setup continous cleaning mode for non-900 series WiFi Roomba devices.
 *
 *  Copyright 2019 Aaron Ward
 *
 *  Special thanks to Dominick Meglio for creating the initial integration and giving me permission to use his code to create this application.
 *  
 *  This App is free and was designed for my needs originally but has grown for most needs too.
 *
 *-------------------------------------------------------------------------------------------------------------------
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *
 * ------------------------------------------------------------------------------------------------------------------------------
 *              Donations are always appreciated: https://www.paypal.me/aaronmward
 * ------------------------------------------------------------------------------------------------------------------------------
 *
 *  Changes:
 *       
 *   1.0.1 - added pushover notification capabilities
 *   1.0.0 - Inital concept from Dominick Meglio
**/
metadata {
    definition (name: "Roomba", namespace: "aaronward", author: "Aaron Ward") {
		capability "Battery"
        capability "Consumable"
		capability "Actuator"
        
        attribute "cleanStatus", "string"
        
        command "start"
        command "stop"
        command "pause"
        command "resume"
        command "dock"
    }
}

def setVersion(){
    appName = "RoombaDriver"
	version = "1.0.1" 
    dwInfo = "${appName}:${version}"
    sendEvent(name: "dwDriverInfo", value: dwInfo, displayed: true)
}

def updateVersion() {
    log.info "In updateVersion"
    setVersion()
}

def start() {
    parent.handleStart(device, device.deviceNetworkId.split(":")[1])
    parent.pushNow("${device} has started cleaning.", start)
}

def stop() {
    parent.handleStop(device, device.deviceNetworkId.split(":")[1])
    parent.pushNow("${device} has stopped cleaning.", stop)
}

def pause() {
    parent.handlePause(device, device.deviceNetworkId.split(":")[1])
    parent.pushNow("${device} has paused cleaning.", pause)
}

def resume() {
    parent.handleResume(device, device.deviceNetworkId.split(":")[1])
    parent.pushNow("${device} has resumed cleaning.", resume)
}

def dock() {
    parent.handleDock(device, device.deviceNetworkId.split(":")[1])
    parent.pushNow("${device} has finished cleaning.", dock)
}
