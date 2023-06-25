package com.data.info.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.data.info.vo.enums.TypeEnum;


public class Type2023062201 {

    public static String get(Integer i){
        String url = TypeEnum.type2023062201.getRule().replace("{NUMBER}", i+"");
        String rs = HttpUtil.createGet(url).execute().body();
        String startStr = "<script type=\"text/javascript\">var player_aaaa=";
        int num = rs.indexOf(startStr);
        if(num > -1){

            String m3u8 = rs.substring(num).replace(startStr, "");
            m3u8=m3u8.substring(0, m3u8.indexOf("</script>"));
            JSONObject jsonObject = JSONObject.parseObject(m3u8);

            String title = rs.substring(rs.indexOf("<b>")).replace("<b>", "");
            title = title.replace("<b>", "");
            title = title.substring(0, title.indexOf("</b>"));
            return jsonObject.getString("url") + "|" + title;
        }

        return null;
    }


}
