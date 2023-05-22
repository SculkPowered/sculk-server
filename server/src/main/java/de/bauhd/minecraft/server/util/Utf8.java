package de.bauhd.minecraft.server.util;

public final class Utf8 {

    public static int encodedLength(CharSequence sequence) {
        int utf16Length = sequence.length();
        int utf8Length = utf16Length;

        int i = 0;
        while (i < utf16Length && sequence.charAt(i) < 128) {
            i++;
        }

        while (i < utf16Length) {
            var c = sequence.charAt(i);
            if (c >= 2048) {
                utf8Length += encodedLengthGeneral(sequence, i);
                break;
            }
            utf8Length += 127 - c >>> 31;
            i++;
        }

        if (utf8Length < utf16Length) {
            var var6 = utf8Length + 4294967296L;
            throw new IllegalArgumentException("UTF-8 length does not fit in int: " + var6);
        } else {
            return utf8Length;
        }
    }

    private static int encodedLengthGeneral(CharSequence sequence, int start) {
        final var utf16Length = sequence.length();
        var utf8Length = 0;

        for (var i = start; i < utf16Length; ++i) {
            var c = sequence.charAt(i);
            if (c < 2048) {
                utf8Length += 127 - c >>> 31;
            } else {
                utf8Length += 2;
                if ('\ud800' <= c && c <= '\udfff') {
                    if (Character.codePointAt(sequence, i) == c) {
                        throw new IllegalArgumentException("Unpaired surrogate at index " + i);
                    }
                    ++i;
                }
            }
        }
        return utf8Length;
    }
}
