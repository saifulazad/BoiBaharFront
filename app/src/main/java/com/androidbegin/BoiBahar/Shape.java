package com.androidbegin.BoiBahar;

import java.util.ArrayList;

public class Shape {
    static char temp;
    static ArrayList<Character> split_matra;

    public static char[] reorder(char[] text) {
        for (int i = 0; i < text.length; i++) {
            if ((text[i] == 'ে') || (text[i] == 'ৈ') || (text[i] == 'ি')) {
                text = swap(text, i);
                if (text[i] == 62794) {
                    text = swap(text, i - 1);
                }

            }

            if ((text[i] == 62797)) {
                if ((text[i + 2] == 'ে') || (text[i + 2] == 'ৈ') || (text[i + 2] == 'ি')) {
                    //	  Log.d("found", "ref with other");
                    temp = text[i];
                    text[i] = text[(i + 2)];
                    text[(i + 2)] = temp;
                    i = i + 2;
                } else {
                    //	  Log.d("found", "ref only");
                    text = swap(text, i + 1);
                    i++;
                }
            }

            if ((text[i] == 'ো') || (text[i] == 'ৌ')) {
                text = split(text, i);
                if ((i - 1 > 0) && (text[i] != 62794)) {
                    text = swap(text, i - 1);
                }

            }

        }

        return text;
    }

    public static char[] split(char[] text, int i) {
        split_matra = new ArrayList();
        for (int j = 0; j < text.length; j++) {
            split_matra.add(Character.valueOf(text[j]));
        }
        if (((Character) split_matra.get(i)).charValue() == 'ো') {
            if (i - 2 >= 0) {
                split_matra.add(i - 2, Character.valueOf('ে'));
                split_matra.remove(i + 1);
                split_matra.add(i + 1, Character.valueOf('া'));
            } else {
                split_matra.add(0, Character.valueOf('ে'));
                split_matra.remove(i + 1);
                split_matra.add(i + 1, Character.valueOf('া'));
            }
        }

        if (((Character) split_matra.get(i)).charValue() == 'ৌ') {
            if (i - 2 >= 0) {
                split_matra.add(i - 2, Character.valueOf('ে'));
                split_matra.remove(i + 1);
                split_matra.add(i + 1, Character.valueOf('ৗ'));
            } else {
                split_matra.add(0, Character.valueOf('ে'));
                split_matra.remove(i + 1);
                split_matra.add(i + 1, Character.valueOf('ৗ'));
            }

        }

        text = new char[split_matra.size()];
        for (int j = 0; j < text.length; j++) {
            text[j] = ((Character) split_matra.get(j)).charValue();
        }
        return text;
    }

    public static char[] swap(char[] text, int i) {
        temp = text[i];
        text[i] = text[(i - 1)];
        text[(i - 1)] = temp;
        return text;
    }
}