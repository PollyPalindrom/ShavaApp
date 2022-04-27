package com.example.shavaapp.common

import java.net.NetworkInterface
import java.util.*

class GetMacAddressManager {

    fun getDeviceMacAddress(): String {
        val networkInterfaceList: List<NetworkInterface> =
            Collections.list(NetworkInterface.getNetworkInterfaces())
        var stringMac = ""
        for (networkInterface in networkInterfaceList) {
            if (networkInterface.name.equals("wlan0")) {
                for (element in networkInterface.hardwareAddress) {
                    var stringMacByte =
                        Integer.toHexString((element.toUByte().and(0xFF.toUByte())).toInt())
                    if (stringMacByte.length == 1) {
                        stringMacByte = "0$stringMacByte"
                    }
                    stringMac = stringMac + stringMacByte.uppercase(Locale.getDefault()) + ":"
                }
                break
            }
        }
        return stringMac.dropLast(1)
    }
}