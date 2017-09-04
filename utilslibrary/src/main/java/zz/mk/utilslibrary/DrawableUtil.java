package zz.mk.utilslibrary;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;

import java.io.InputStream;

public class DrawableUtil {

    public static StateListDrawable addStateDrawable(Context context,
                                                     int idNormal, int idPressed, int idFocused) {
        StateListDrawable drawable = new StateListDrawable();
        // Non focused states
        drawable.addState(
                new int[]{-android.R.attr.state_focused,
                        -android.R.attr.state_selected,
                        -android.R.attr.state_pressed}, context.getResources()
                        .getDrawable(idNormal));
        drawable.addState(new int[]{-android.R.attr.state_focused,
                        android.R.attr.state_selected, -android.R.attr.state_pressed},
                context.getResources().getDrawable(idFocused));
        // Focused states
        drawable.addState(
                new int[]{android.R.attr.state_focused,
                        -android.R.attr.state_selected,
                        -android.R.attr.state_pressed}, context.getResources()
                        .getDrawable(idFocused));
        drawable.addState(new int[]{android.R.attr.state_focused,
                        android.R.attr.state_selected, -android.R.attr.state_pressed},
                context.getResources().getDrawable(idFocused));
        // Pressed
        drawable.addState(new int[]{android.R.attr.state_selected,
                android.R.attr.state_pressed}, context.getResources()
                .getDrawable(idPressed));
        drawable.addState(new int[]{android.R.attr.state_pressed}, context
                .getResources().getDrawable(idPressed));
        return drawable;
    }

    /**
     * 点击选中效果
     *
     * @param context
     * @param idNormalinputStream
     * @param idPressedinputStream
     * @return
     */
    public static StateListDrawable addStateListInputStream(Context context,
                                                            InputStream idNormalinputStream, InputStream idPressedinputStream) {
        StateListDrawable drawable = new StateListDrawable();
        Drawable idNormalDraw = new BitmapDrawable(context.getResources(), idNormalinputStream);
        Drawable idPressedDraw = new BitmapDrawable(context.getResources(), idPressedinputStream);

        drawable.addState(new int[]{android.R.attr.state_selected}, idPressedDraw);
        drawable.addState(new int[]{android.R.attr.state_enabled}, idNormalDraw);
        drawable.addState(new int[]{}, idNormalDraw);
        return drawable;
    }

    /**
     * 点击选中效果
     *
     * @param context
     * @param idNormal
     * @param idPressed
     * @return
     */
    public static StateListDrawable addStateListIntDrawable(Context context,
                                                            int idNormal, int idPressed) {
        StateListDrawable drawable = new StateListDrawable();

        drawable.addState(new int[]{android.R.attr.state_selected}, context
                .getResources().getDrawable(idPressed));
        drawable.addState(new int[]{android.R.attr.state_enabled}, context
                .getResources().getDrawable(idNormal));
        drawable.addState(new int[]{},
                context.getResources().getDrawable(idNormal));
        return drawable;
    }

    /**
     * 点击效果
     *
     * @param context
     * @param idNormal
     * @param idPressed
     * @return
     */
    public static StateListDrawable addStateListDrawable(Context context,
                                                         int idNormal, int idPressed) {
        StateListDrawable drawable = new StateListDrawable();

        drawable.addState(new int[]{android.R.attr.state_pressed}, context
                .getResources().getDrawable(idPressed));
        drawable.addState(new int[]{android.R.attr.state_focused}, context
                .getResources().getDrawable(idPressed));
        drawable.addState(new int[]{},
                context.getResources().getDrawable(idNormal));
        return drawable;
    }

    /**
     * drawable 着色
     *
     * @param
     * @param colors
     * @return
     */
    public static Drawable tintDrawable(Context context, int res, int colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(context.getResources().getDrawable(res).mutate());
        DrawableCompat.setTintList(wrappedDrawable, ColorStateList.valueOf(colors));
        return wrappedDrawable;
    }

    public static StateListDrawable addStateListTintDrawable(Context context,
                                                             Drawable idNormal, Drawable idPressed) {
        StateListDrawable drawable = new StateListDrawable();

        drawable.addState(new int[]{android.R.attr.state_pressed}, idPressed);
        drawable.addState(new int[]{android.R.attr.state_focused}, idPressed);
        drawable.addState(new int[]{}, idNormal);
        return drawable;
    }

	public static StateListDrawable setBackgroud(Context context,
														 Drawable idNormal, Drawable idPressed) {
		StateListDrawable drawable = new StateListDrawable();

		drawable.addState(new int[] { android.R.attr.state_pressed }, idNormal);
		drawable.addState(new int[] { android.R.attr.state_focused }, idNormal);
		drawable.addState(new int[] {}, idNormal);
		return drawable;
	}

	/**
	 * 设置 Shape
	 *
	 * @return
	 */
	public static GradientDrawable backgroundDrawable(int strokeWidth, String strokeColor, String fillColor, float raduis) {
		GradientDrawable gradientDrawable = new GradientDrawable();
		gradientDrawable.setColor(Color.parseColor(fillColor));
		gradientDrawable.setCornerRadius(raduis);
		gradientDrawable.setStroke(strokeWidth, Color.parseColor(strokeColor));
		return gradientDrawable;
	}

	/**
	 * 文字点击颜色
	 *
	 * @param context
	 * @param idNormal
	 * @param idPressed
	 * @return
	 */
	/** 对TextView设置不同状态时其文字颜色。 */
	public static ColorStateList getColorStateList(String selected, String normal) {
		int[] colors = new int[] {Color.parseColor(selected), Color.parseColor(normal)};
		int[][] states = new int[2][];
		states[0] = new int[] { android.R.attr.state_selected};
		states[1] = new int[] {};
		ColorStateList colorList = new ColorStateList(states, colors);
		return colorList;
	}
}
