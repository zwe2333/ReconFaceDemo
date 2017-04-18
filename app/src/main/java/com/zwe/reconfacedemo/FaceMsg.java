package com.zwe.reconfacedemo;

import java.util.List;

/**
 * Created by Asus on 2017/4/18.
 */

public class FaceMsg {

    /**
     * result_num : 1
     * result : [{"location":{"left":209,"top":142,"width":113,"height":119},"face_probability":0.93339461088181,"rotation_angle":10,"yaw":28.257530212402,"pitch":2.914573431015,"roll":15.187321662903,"gender":"male","gender_probability":0.9174684882164}]
     * log_id : 2226452894
     */

    private int result_num;
    private long log_id;
    /**
     * location : {"left":209,"top":142,"width":113,"height":119}
     * face_probability : 0.93339461088181
     * rotation_angle : 10
     * yaw : 28.257530212402
     * pitch : 2.914573431015
     * roll : 15.187321662903
     * gender : male
     * gender_probability : 0.9174684882164
     */

    private List<ResultBean> result;

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * left : 209
         * top : 142
         * width : 113
         * height : 119
         */

        private LocationBean location;
        private double face_probability;
        private int rotation_angle;
        private double yaw;
        private double pitch;
        private double roll;
        private String gender;
        private double gender_probability;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public double getFace_probability() {
            return face_probability;
        }

        public void setFace_probability(double face_probability) {
            this.face_probability = face_probability;
        }

        public int getRotation_angle() {
            return rotation_angle;
        }

        public void setRotation_angle(int rotation_angle) {
            this.rotation_angle = rotation_angle;
        }

        public double getYaw() {
            return yaw;
        }

        public void setYaw(double yaw) {
            this.yaw = yaw;
        }

        public double getPitch() {
            return pitch;
        }

        public void setPitch(double pitch) {
            this.pitch = pitch;
        }

        public double getRoll() {
            return roll;
        }

        public void setRoll(double roll) {
            this.roll = roll;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public double getGender_probability() {
            return gender_probability;
        }

        public void setGender_probability(double gender_probability) {
            this.gender_probability = gender_probability;
        }

        public static class LocationBean {
            private int left;
            private int top;
            private int width;
            private int height;

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
