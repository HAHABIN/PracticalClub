/**
 * 文 件 名:  MarkdownUtils
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/8/20
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.common.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.Target;
import com.example.common.R;
import com.example.common.base.WebActivity;

import org.commonmark.node.Node;

import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.LinkResolver;
import io.noties.markwon.Markwon;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.core.MarkwonTheme;
import io.noties.markwon.image.AsyncDrawable;
import io.noties.markwon.image.ImagesPlugin;
import io.noties.markwon.image.gif.GifMediaDecoder;
import io.noties.markwon.image.glide.GlideImagesPlugin;
import io.noties.markwon.syntax.SyntaxHighlightNoOp;

/**
 * <富文本Markdown工具> <功能详细描述>
 * 如果需要html 在以下链接添加新的依赖 并重新配置
 * https://noties.io/Markwon/docs/v4/html/#predefined-taghandlers
 * @author HABIN
 * @version 2020/8/20
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MarkdownUtils {


    private static MarkdownUtils sInstance;

    private Context mContext;
    private final Markwon markdwon;

    public MarkdownUtils() {
        mContext = Utils.getContext();
        markdwon = Markwon.builder(mContext)
                .usePlugin(ImagesPlugin.create(new ImagesPlugin.ImagesConfigure() {
                    @Override
                    public void configureImages(@NonNull ImagesPlugin plugin) {
                        // autoplayGif controls if GIF should be automatically started
                        plugin.addMediaDecoder(GifMediaDecoder.create(/*autoplayGif*/true));
                    }
                }))
                // automatically create Glide instance
                .usePlugin(GlideImagesPlugin.create(mContext))
                // use supplied Glide instance
                .usePlugin(GlideImagesPlugin.create(Glide.with(mContext)))
                // if you need more control
                .usePlugin(GlideImagesPlugin.create(new GlideImagesPlugin.GlideStore() {
                    @NonNull
                    @Override
                    public RequestBuilder<Drawable> load(@NonNull AsyncDrawable drawable) {
                        return Glide.with(mContext).load(drawable.getDestination());
                    }

                    @Override
                    public void cancel(@NonNull Target<?> target) {
                        Glide.with(mContext).clear(target);
                    }
                }))
                .usePlugin(new AbstractMarkwonPlugin() {

                    @Override
                    public void configureTheme(@NonNull MarkwonTheme.Builder builder) {
                        builder
                                .headingBreakHeight(1)
                                .headingTypeface(Typeface.DEFAULT_BOLD)
                                .codeTypeface(Typeface.MONOSPACE)
                                .codeTextColor(Utils.getColor(R.color.color_e83e8c))
                                .codeBackgroundColor(Utils.getColor(R.color.color_1d1f20))
                                .codeBlockTypeface(Typeface.MONOSPACE)
                                .codeBlockBackgroundColor(Utils.getColor(R.color.color_2d2d2d))
                                .codeBlockTextColor(Utils.getColor(R.color.color_cccccc))

                        ;


                    }
                })
                .usePlugin(new AbstractMarkwonPlugin() {
                    @Override
                    public void configureConfiguration(@NonNull MarkwonConfiguration.Builder builder) {
                        builder.linkResolver(new LinkResolver() {
                            @Override
                            public void resolve(@NonNull View view, @NonNull String link) {
                                WebActivity.startActivity(mContext,link);
                            }
                        })
                                .syntaxHighlight(new SyntaxHighlightNoOp())

                        ;
                    }
                })
                .build();

    }

    /**
     * @return
     */
    public static MarkdownUtils getInstance() {
        if (sInstance == null) {
            synchronized (MarkdownUtils.class) {
                sInstance = new MarkdownUtils();
            }
        }
        return sInstance;
    }

    /**
     * @param mTvContent 显示控件
     * @param content 显示内容
     */
    public void setParsedMarkdown(TextView mTvContent,String content){
        // parse markdown to commonmark-java Node
        final Node node = markdwon.parse(content);

        // create styled text from parsed Node
        final Spanned markdown = markdwon.render(node);

        // use it on a TextView
        markdwon.setParsedMarkdown(mTvContent, markdown);


    }


}
