package com.best.bean;

/**
 * Created by Z on 2016/1/5.
 */
public class ShangPin {
    private String goodsId;
    private String goodsSn;
    private String goodsName;
    private String goodsImg;
    private String goodsThums;
    private String marketPrice;
    private String goodsStock;

    public ShangPin(String goodsId, String goodsSn, String goodsName, String goodsImg, String goodsThums, String marketPrice, String goodsStock) {
        this.goodsId = goodsId;
        this.goodsSn = goodsSn;
        this.goodsName = goodsName;
        this.goodsImg = goodsImg;
        this.goodsThums = goodsThums;
        this.marketPrice = marketPrice;
        this.goodsStock = goodsStock;
    }

    public ShangPin() {
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsThums() {
        return goodsThums;
    }

    public void setGoodsThums(String goodsThums) {
        this.goodsThums = goodsThums;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(String goodsStock) {
        this.goodsStock = goodsStock;
    }

    @Override
    public String toString() {
        return "ShangPin{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsSn='" + goodsSn + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsThums='" + goodsThums + '\'' +
                ", marketPrice='" + marketPrice + '\'' +
                ", goodsStock='" + goodsStock + '\'' +
                '}';
    }
}
