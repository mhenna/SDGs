package com.techdev.sdg.Entity;


import java.util.Map;


public abstract class Entity {

        public void setName(String name) { };

        public abstract void setEmail(String email);

        public abstract void setPassword(String password);

        public abstract String getName();

        public abstract String getEmail();

        public abstract String getPassword();

        public abstract Map<String, Object> toMap();

        public abstract String toString();

        public abstract String getType();


    }

