<script id="hb-technologies" type="text/x-handlebars-template"> 
	<li class="kb-tile selected" id="{{uid}}">
		<ul>
		{{#each json-result}}
			<li>
				{{#if question}}
					<div style="display:none" class="currentQuestionid">
						{{id}}
					</div>				
					<div class="question">
						{{question}}
					</div>
					{{#each answerChoices}}
						<div class="answer">
							<span style="display:none" id="hilit_element">div</span>
							<span style="display:none" id="ac_id">{{id}}</span>
							<span style="display:none" id="q_id">{{nextQuestId}}</span>
							<a>
								{{ansChoice}} 
							</a>	
							
							{{#if deletable}}
								<img src="assets/images/delete.jpg" class="deleteIcon"  />
							{{/if}}
														
						</div>	
					{{/each}}	
				{{else}}
					<div style="display:none" class="parentTechnologyid">
						{{parentId}}
					</div>	
					<div class="technology">
						<span style="display:none" id="hilit_element">li</span>
						<span style="display:none" id="t_id">{{id}}</span>
						<span style="display:none" id="q_id">{{questionId}}</span>
						<a>
							{{name}} 
						</a>
						{{#if deletable}}
							<img src="assets/images/delete.jpg" class="deleteIcon" />
						{{/if}}
					</div>
				 {{/if}}
			</li>	
		 {{/each}}
		</ul>
	</li>
</script>	


