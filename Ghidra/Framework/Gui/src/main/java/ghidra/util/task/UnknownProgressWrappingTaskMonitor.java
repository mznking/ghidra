/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.util.task;

/**
 * A class that is meant to wrap a {@link TaskMonitor} when you do not know the maximum value
 * of the progress.
 */
public class UnknownProgressWrappingTaskMonitor extends WrappingTaskMonitor {

	public UnknownProgressWrappingTaskMonitor(TaskMonitor delegate) {
		this(delegate, 0);
	}

	public UnknownProgressWrappingTaskMonitor(TaskMonitor delegate, long startMaximum) {
		super(delegate);
		delegate.setMaximum(startMaximum);
	}

	@Override
	public void setProgress(long value) {
		super.setProgress(value);
		maybeUpdateMaximum();
	}

	@Override
	public void incrementProgress(long incrementAmount) {
		super.incrementProgress(incrementAmount);
		maybeUpdateMaximum();
	}

	private void maybeUpdateMaximum() {
		long currentMaximum = delegate.getMaximum();
		long progress = delegate.getProgress();

		long _75_percent = currentMaximum - (currentMaximum / 4);
		if (progress > _75_percent) {
			delegate.setMaximum(
				Math.max(Math.max(progress, 4), currentMaximum + currentMaximum / 4));
		}
	}

}
