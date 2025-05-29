package com.learnings.markup.markuplive.util;

import com.learnings.markup.markuplive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class AccountGenerationUtil {
    public static Long getAccount() {
        Random random = new Random();
        int min = (int)Math.pow(10,4);
        int max = (int)Math.pow(10,9);
        return min+(long)(random.nextDouble(max-min+1));
    }
}
