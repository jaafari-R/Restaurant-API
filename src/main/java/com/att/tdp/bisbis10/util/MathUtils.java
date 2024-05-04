package com.att.tdp.bisbis10.util;

import java.util.List;

public class MathUtils {
    static public float sum(List<Float> nums) {
        float sum = 0;
        for (Float num : nums) {
            sum += num != null ? num : 0;
        }
        return sum;
    }

    static public float average(List<Float> nums) {
        if(nums.size() == 0) {
            return 0;
        }

        float sum = sum(nums);
        return sum / nums.size(); 
    }

    static public float lastTwoDecimalsFloat(float num) {
        return Math.round(num * 100f) / 100f;
    }
}
