package com.taotao.portal.controller;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;

/**
 * <p>Title:com.taotao.portal.controller</p>
 * <p>description: </p>
 *
 * @Author: 司马懿
 * @data: 2017/6/11.
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q") String keyword,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "60") Integer rows, Model model) {
        try {
            //keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
           // System.out.println(URLEncoder.encode("This string has spaces","UTF-8"));
            keyword = URLDecoder.decode(keyword , "utf-8");
        } catch (Exception e) {
            keyword = "";
            e.printStackTrace();
        }


        System.out.println(keyword+"-------------------");
        //keyword = "手机";
        SearchResult searchResult = searchService.search(keyword, page, rows);
        model.addAttribute("query", keyword);
          // model.addAttribute("query", "手机");
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", searchResult.getCurPage());

        System.out.println(searchResult.getItemList());

        return "search";
    }
}
