package de.maxbossing.mxcord.utils.extensions


fun String.containsAny(vararg matches: String): Boolean  {
    for (match in matches) {
        if (this.contains(match))
            return true
        else
            continue
    }
    return false
}