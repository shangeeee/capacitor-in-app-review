declare module '@capacitor/core' {
    interface PluginRegistry {
        InAppReview: InAppReviewPlugin;
    }
}
export interface InAppReviewPlugin {
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
