package com.jyuno426.hangul.plugin;

import java.util.HashMap;
import java.util.Map;
import com.jyuno426.hangul.filters.chosung.ChosungFilterFactory;
import com.jyuno426.hangul.filters.engtohan.EngToHanFilterFactory;
import com.jyuno426.hangul.filters.hantoeng.HanToEngFilterFactory;
import com.jyuno426.hangul.filters.jamo.JamoDecomposeFilterFactory;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule.AnalysisProvider;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

public class HangulPlugin extends Plugin implements AnalysisPlugin {

	@Override
	public Map<String, AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
		Map<String, AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();
		extra.put("chosung", ChosungFilterFactory::new);
		extra.put("jamo", JamoDecomposeFilterFactory::new);
		extra.put("engtohan", EngToHanFilterFactory::new);
		extra.put("hantoeng", HanToEngFilterFactory::new);

		return extra;
	}
}
