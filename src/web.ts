import { WebPlugin } from '@capacitor/core';
import { InAppReviewPlugin } from './definitions';

export class InAppReviewWeb extends WebPlugin implements InAppReviewPlugin {
  constructor() {
    super({
      name: 'InAppReview',
      platforms: ['web'],
    });
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async launchReview(options: { value: string }): Promise<{ value: string }> {
    console.error('launchReview: you should have to redirect to play.goole.com instead', options);
    return options;
  }
}

const InAppReview = new InAppReviewWeb();

export { InAppReview };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(InAppReview);
