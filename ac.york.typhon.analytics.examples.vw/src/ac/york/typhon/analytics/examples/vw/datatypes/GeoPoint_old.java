/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package ac.york.typhon.analytics.examples.vw.datatypes;


/**
 *
 */
public final class GeoPoint_old {

    private double lat;
    private double lon;

    public GeoPoint_old() {
    }

    /**
     * Create a new Geopointform a string. This String must either be a geohash
     * or a lat-lon tuple.
     *   
     * @param value String to create the point from
     */
//    public GeoPoint(String value) {
//        this.resetFromString(value);
//    }

    public GeoPoint_old(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public GeoPoint_old reset(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        return this;
    }

    public GeoPoint_old resetLat(double lat) {
        this.lat = lat;
        return this;
    }

    public GeoPoint_old resetLon(double lon) {
        this.lon = lon;
        return this;
    }

//    public GeoPoint resetFromString(String value) {
//        int comma = value.indexOf(',');
//        if (comma != -1) {
//            lat = Double.parseDouble(value.substring(0, comma).trim());
//            lon = Double.parseDouble(value.substring(comma + 1).trim());
//        } else {
//            resetFromGeoHash(value);
//        }
//        return this;
//    }

//    public GeoPoint resetFromGeoHash(String hash) {
//        GeoHashUtils.decode(hash, this);
//        return this;
//    }

    public final double lat() {
        return this.lat;
    }

    public final double getLat() {
        return this.lat;
    }

    public final double lon() {
        return this.lon;
    }

    public final double getLon() {
        return this.lon;
    }

//    public final String geohash() {
//        return GeoHashUtils.encode(lat, lon);
//    }
//
//    public final String getGeohash() {
//        return GeoHashUtils.encode(lat, lon);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoPoint_old geoPoint = (GeoPoint_old) o;

        if (Double.compare(geoPoint.lat, lat) != 0) return false;
        if (Double.compare(geoPoint.lon, lon) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = lat != +0.0d ? Double.doubleToLongBits(lat) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = lon != +0.0d ? Double.doubleToLongBits(lon) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "[" + lat + ", " + lon + "]";
    }

//    public static GeoPoint parseFromLatLon(String latLon) {
//        GeoPoint point = new GeoPoint();
//        point.resetFromString(latLon);
//        return point;
//    }
}
