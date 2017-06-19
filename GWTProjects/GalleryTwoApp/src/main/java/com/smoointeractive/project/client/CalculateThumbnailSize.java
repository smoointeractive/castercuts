package com.smoointeractive.project.client;

/**
 * Created by sachamoo on 6/15/17.
 */
public class CalculateThumbnailSize {
    private static int maxWidth = 0;
    private static int columnCount = 0;
    private static int screenSize = 0;
    private static int thumbnailPadding = 20;

    public static int getMaxWidth(int columns, int rootPanelSize)
    {
        columnCount = columns;
        screenSize = rootPanelSize;
        if(0 != columnCount && 0 != screenSize) {
            maxWidth = (int) (Math.round(screenSize/ columnCount)-(thumbnailPadding*2));
        }

        return maxWidth;
    }
}
