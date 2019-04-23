package com.moon.studentplatform.dto.official;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/22 11:13
 * @description：这个类是
 * @modified By：
 */
public class LectureSeach {
    private int offset;
    private int limit;
    private Integer typeId;
    private String keyWord;

    public LectureSeach() {
    }

    public LectureSeach(int offset, int limit, Integer typeId, String keyWord) {
        this.offset = offset;
        this.limit = limit;
        this.typeId = typeId;
        this.keyWord = keyWord;
    }

    public LectureSeach(Integer typeId, String keyWord) {
        this.typeId = typeId;
        this.keyWord = keyWord;
    }
}
