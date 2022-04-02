package com.jyuno426.hangul.filters.engtohan;

import java.io.IOException;
import com.jyuno426.hangul.util.HanEngUtil;
import com.jyuno426.hangul.util.JamoUtil;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class EngToHanFilter extends TokenFilter {

	private final CharTermAttribute charAttr;
	private final JamoUtil jamoUtil;
	private final HanEngUtil hanEngUtil;

	public EngToHanFilter(TokenStream input) {
		super(input);
		jamoUtil = new JamoUtil();
		hanEngUtil = new HanEngUtil();
		charAttr = addAttribute(CharTermAttribute.class);
	}

	@Override
	public final boolean incrementToken() throws IOException {
		if (input.incrementToken()) {
			String engToHan = jamoUtil.compose(hanEngUtil.transformEnglishToHangul(charAttr.toString()));
			charAttr.setEmpty().append(engToHan);
			return true;
		}

		return false;
	}
}
