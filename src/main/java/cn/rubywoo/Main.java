package cn.rubywoo;

import cn.rubywoo.service.WeiboNotify;

import java.io.IOException;

/**
 * @ClassNameMain
 * @Description TODO
 * @Author apple
 * @Date 2019/4/5 3:04 PM
 * @Version 1.0
 */
public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {
        WeiboNotify.notifyWechat();
    }
}
