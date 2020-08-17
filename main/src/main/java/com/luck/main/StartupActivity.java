package com.luck.main;

import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.Target;
import com.luck.main.R2;
import com.example.common.base.BaseActivity;

import org.commonmark.node.Node;

import butterknife.BindView;
import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.LinkResolverDef;
import io.noties.markwon.Markwon;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.MarkwonPlugin;
import io.noties.markwon.image.AsyncDrawable;
import io.noties.markwon.image.ImagesPlugin;
import io.noties.markwon.image.gif.GifMediaDecoder;
import io.noties.markwon.image.glide.GlideImagesPlugin;
import io.noties.markwon.syntax.SyntaxHighlight;
import io.noties.markwon.syntax.SyntaxHighlightNoOp;


public class StartupActivity extends BaseActivity {


    @BindView(R2.id.tv_content)
    TextView mTvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_startup;
    }

    @Override
    protected void initView() {

        String contents = "使用 `ViewPager2` 实现无限轮播效果，可以用来实现 banner 以及上下滚动文字广告等。\n" +
                "\n" +
                "## Screenshots\n" +
                "\n" +
                "![](https://gank.io/images/dff02e8101f34494a58876f05d88bfd9)\n" +
                "\n" +
                "## Features\n" +
                "\n" +
                "* 支持无限自动轮播\n" +
                "* 支持水平竖直方向\n" +
                "* 支持圆点指示符及自定义\n" +
                "* 支持一屏显示 3 个 item 的切换效果\n" +
                "* 支持 ZoomOutPageTransformer & DepthPageTransformer 效果\n" +
                "\n" +
                "## Getting started\n" +
                "\n" +
                "在项目的根节点的 `build.gradle` 中添加如下代码\n" +
                "```\n" +
                "allprojects {\n" +
                "    repositories {\n" +
                "        ...\n" +
                "        maven { url 'https://jitpack.io' }\n" +
                "    }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "在项目的 `build.gradle` 中添加\n" +
                "```\n" +
                "dependencies {\n" +
                "    implementation 'com.github.wangpeiyuan:CycleViewPager2:v1.0.7'\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "**注意：还需要自行添加 ViewPager2 的依赖**。如：\n" +
                "```\n" +
                "dependencies {\n" +
                "    implementation androidx.viewpager2:viewpager2:1.0.0\n" +
                "}\n" +
                "``` \n" +
                "\n" +
                "ViewPager2 最新版本请点击[**此处**](https://developer.android.com/jetpack/androidx/releases/viewpager2)\n" +
                "\n" +
                "## Usage\n" +
                "\n" +
                "1. 在 XML 布局文件中：\n" +
                "```\n" +
                "<com.wangpeiyuan.cycleviewpager2.CycleViewPager2\n" +
                "        android:id=@+id/banner\n" +
                "        android:layout_width=0dp\n" +
                "        android:layout_height=250dp\n" +
                "        android:layout_marginTop=16dp\n" +
                "        app:layout_constraintEnd_toEndOf=parent\n" +
                "        app:layout_constraintStart_toStartOf=parent\n" +
                "        app:layout_constraintTop_toTopOf=parent />\n" +
                "```\n" +
                "\n" +
                "2. 继承 `CyclePagerAdapter` 或 `CyclePagerFragmentAdapter` 实现相关方法，跟 `RecyclerView` 的 `Adapter` 基本类似，如：\n" +
                "```\n" +
                "private inner class MyCyclePagerAdapter : CyclePagerAdapter<PagerViewHolder>() {\n" +
                "    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {\n" +
                "        return PagerViewHolder(LayoutInflater.from(parent.context)\n" +
                "                .inflate(R.layout.fragment_pager, parent, false)\n" +
                "        )\n" +
                "    }\n" +
                "\n" +
                "    override fun getRealItemCount(): Int = items.size\n" +
                "\n" +
                "    override fun onBindRealViewHolder(holder: PagerViewHolder, position: Int) {\n" +
                "        holder.ivPager.setImageResource(items[position])\n" +
                "        holder.tvTitle.text = position.toString()\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "private inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {\n" +
                "    var ivPager: ImageView = itemView.findViewById(R.id.iv_pager)\n" +
                "    var tvTitle: TextView = itemView.findViewById(R.id.tv_title)\n" +
                "} \n" +
                "```\n" +
                "\n" +
                "3. 通过 `CycleViewPager2` 设置相关参数。\n" +
                "``` \n" +
                "//设置 adapter，此 adapter 必须是继承自 CyclePagerAdapter 或 CyclePagerFragmentAdapter\n" +
                "cycleViewPager2.setAdapter(adapter)\n" +
                "//设置指示符\n" +
                "cycleViewPager2.setIndicator(indicator)\n" +
                "//设置自动轮播间隔\n" +
                "cycleViewPager2.setAutoTurning(autoTurningTime)\n" +
                "//设置切换效果，可以多个效果组合\n" +
                "cycleViewPager2.setPageTransformer(compositePageTransformer)\n" +
                "//添加间距\n" +
                "cycleViewPager2.addItemDecoration(itemDecoration)\n" +
                "//添加切换监听\n" +
                "cycleViewPager2.registerOnPageChangeCallback(pageChangeCallback)\n" +
                "\n" +
                "cycleViewPager2.setOffscreenPageLimit(limit)\n" +
                "\n" +
                "cycleViewPager2.setOrientation(orientation)\n" +
                "```\n" +
                "\n" +
                "或者使用 `CycleViewPager2Helper` 方式。\n" +
                "``` \n" +
                "CycleViewPager2Helper(banner)\n" +
                "            .setAdapter(adapter)\n" +
                "            .setMultiplePagerScaleInTransformer(\n" +
                "                nextItemVisiblePx.toInt(),\n" +
                "                currentItemHorizontalMarginPx.toInt(),\n" +
                "                0.1f\n" +
                "            )\n" +
                "            .setDotsIndicator(\n" +
                "                dotsRadius,\n" +
                "                Color.RED,\n" +
                "                Color.WHITE,\n" +
                "                dotsPadding,\n" +
                "                0,\n" +
                "                dotsBottomMargin.toInt(),\n" +
                "                0,\n" +
                "                DotsIndicator.Direction.CENTER\n" +
                "            )\n" +
                "            .setAutoTurning(3000L)\n" +
                "            .build()\n" +
                "```\n" +
                "\n" +
                "4. 更多特殊效果\n" +
                "\n" +
                "* 指示符（Indicator），目前库中仅简单实现了圆点的指示符 `DotsIndicator`，更多的效果可以实现 `Indicator` 接口自定义，具体可以参考 `DotsIndicator` 的实现。\n" +
                "* `MultiplePagerScaleInTransformer`，实现了一屏多个的效果，这个效果需要配合 `MarginItemDecoration` 来使用（也可以通过设置 item 的间距达到相同的效果）。\n" +
                "* 更多的切换效果（PageTransformer），需要自己实现 `ViewPager2.PageTransformer`。\n" +
                "\n" +
                "## License\n" +
                "\n" +
                "Apache License, Version 2.0";
        final Markwon markwon = Markwon.builder(mContext)
                .usePlugin(ImagesPlugin.create(new ImagesPlugin.ImagesConfigure() {
                    @Override
                    public void configureImages(@NonNull ImagesPlugin plugin) {
                        // autoplayGif controls if GIF should be automatically started
                        plugin.addMediaDecoder(GifMediaDecoder.create(/*autoplayGif*/false));
                    }
                }))
                .usePlugin(new AbstractMarkwonPlugin() {
                    @Override
                    public void configureConfiguration(@NonNull MarkwonConfiguration.Builder builder) {
                        builder.linkResolver(new LinkResolverDef())
                                .syntaxHighlight(new SyntaxHighlightNoOp());
                    }
                })
                .build();

        // parse markdown to commonmark-java Node
        final Node node = markwon.parse(contents);

        // create styled text from parsed Node
        final Spanned markdown = markwon.render(node);

        // use it on a TextView
        markwon.setParsedMarkdown(mTvContent, markdown);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


}