import { WebPlugin } from '@capacitor/core';
import { InAppReviewPlugin } from './definitions';
export declare class InAppReviewWeb extends WebPlugin implements InAppReviewPlugin {
    constructor();
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
    launchReview(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
}
declare const InAppReview: InAppReviewWeb;
export { InAppReview };
