package com.atguigu.mtime.bean;

import java.util.List;

/**
 * Created by HanFeng on 2015/12/12.
 */
public class BeanTest {


    /**
     * type : -1
     * typeName : 显示所有
     */

    private List<ImageTypesEntity> imageTypes;
    /**
     * id : 7137697
     * image : http://img31.mtime.cn/pi/2015/12/01/091714.44751540_1280X720X2.jpg
     * type : 1
     */

    private List<ImagesEntity> images;

    public void setImageTypes(List<ImageTypesEntity> imageTypes) {
        this.imageTypes = imageTypes;
    }

    public void setImages(List<ImagesEntity> images) {
        this.images = images;
    }

    public List<ImageTypesEntity> getImageTypes() {
        return imageTypes;
    }

    public List<ImagesEntity> getImages() {
        return images;
    }

    public static class ImageTypesEntity {
        private int type;
        private String typeName;

        public void setType(int type) {
            this.type = type;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getType() {
            return type;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    public static class ImagesEntity {
        private int id;
        private String image;
        private int type;

        public void setId(int id) {
            this.id = id;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public int getType() {
            return type;
        }
    }
}
