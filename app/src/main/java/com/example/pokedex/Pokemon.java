package com.example.pokedex;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String nom;
    private String imgUrl;
    private float weight;
    private float height;
    private int hp;
    private int speed;
    private int attack;
    private int defense;
    private float xp;

    public Pokemon(String nom, String imgUrl, float weight, float height, int hp, int speed, int attack, int defense, float xp) {
        this.nom = nom;
        this.imgUrl = imgUrl;
        this.weight = weight;
        this.height = height;
        this.hp = hp;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.xp = xp;
    }

    public String getNom() {
        return nom;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public float getXp() {
        return xp;
    }
}

