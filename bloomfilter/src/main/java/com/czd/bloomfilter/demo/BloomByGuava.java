package com.czd.bloomfilter.demo;

import com.google.common.cache.CacheBuilder;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.Charset;

/**
 * 通过guava实现布隆过滤
 *
 * @author: czd
 * @create: 2018/12/28 14:30
 */
public class BloomByGuava {
    private int size = 1024*1024;
    private BloomFilter<String> bloom = BloomFilter.create(new Funnel<String>() {
        @Override
        public void funnel(String from, PrimitiveSink into) {
            // 自定义过滤条件 此处不做任何过滤
            into.putString((CharSequence)from, Charset.forName("UTF-8"));
        }
    }, size);

    public void test() {
        for (int i = 0; i < 1000; i++) {
            bloom.put(String.valueOf(i));
        }
        boolean mightContain = bloom.mightContain("1500");
        System.out.println(mightContain);
    }

    public static void main(String[] args) {
        BloomByGuava bloom = new BloomByGuava();
        bloom.test();
    }
}
